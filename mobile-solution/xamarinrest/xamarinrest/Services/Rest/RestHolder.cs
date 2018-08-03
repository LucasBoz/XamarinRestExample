using xamarinrest.Configuration;
using xamarinrest.Database;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace xamarinrest.Services.Rest
{
    class RestHolder<T>
    {
        private static readonly TimeSpan refreshTimeSpan = new TimeSpan( 0, 0, 30 );

        //self instance singleton para RestEntityHolder, que auxilia a mexer com as Uri's REST
        public static RestHolder<T> instance = null;

        private string _syncUri;
        private string _insertUri;
        private string _updateUri;
        private string _deleteUri;

        public RestHolder()
        {
            instance = instance ?? this;
        }

        public bool LockThread { get; set; }

        public string InsertUri
        {
            get => _insertUri ?? typeof(T).Name.ToLower() + "/" + "insert";
            set => _insertUri = value;
        }

        public string UpdateUri
        {
            get => _updateUri ?? typeof(T).Name.ToLower() + "/" + "update";
            set => _updateUri = value;
        }

        public string DeleteUri
        {
            get => _deleteUri ?? typeof(T).Name.ToLower() + "/" + "delete";
            set => _deleteUri = value;
        }

        public string SyncUri
        {
            get => _syncUri ?? typeof(T).Name.ToLower() + "/" + "list";
            set => _syncUri = value;
        }

        public static void StartAutoSync<T>() where T : new()
        {
            RunTask<T>();

            var holder = RestHolder<T>.instance;
            Device.StartTimer( refreshTimeSpan, () => {
                RunTask<T>();
                return true; //restart timer
            });
        }

        private static async void RunTask<T>() where T : new()
        {
            var holder = RestHolder<T>.instance;
            if (holder.LockThread) return;
            holder.LockThread = true;

            try
            {
                //Pega o DateTime da ultima requisição desta Uri
                DateTime lastRequest = Prefs.getDateTime(holder.SyncUri);

                //Request e Sync
                long unixTimestamp = lastRequest.Ticks - new DateTime(1970, 1, 1).Ticks;

                Console.WriteLine("-------- Init REST");
                string content = await RestService.GetAsync(holder.SyncUri + ( unixTimestamp / TimeSpan.TicksPerMillisecond ) );
                Console.WriteLine("-------- END REST seconds(" + ( unixTimestamp / TimeSpan.TicksPerSecond ) + ")");

                Console.WriteLine("-------- Init Deserialize ");
                List<T> list2 = JsonConvert.DeserializeObject<List<T>>(content);
                Console.WriteLine("-------- END Deserialize seconds(" + (unixTimestamp / TimeSpan.TicksPerSecond) + ")");

                Console.WriteLine("-------- Init Sync");
                SQLiteRepository.Sync<T>(list2);
                Console.WriteLine("-------- END SYNC seconds(" + (unixTimestamp / TimeSpan.TicksPerSecond) + ")");


                //Seta o DateTime da ultima requisição para AGORA
                Prefs.setDateTime(holder.SyncUri, DateTime.Now);
                holder.LockThread = false;
            }
            catch( Exception e )
            {
                holder.LockThread = false;
                Console.WriteLine( e.Message );
            }
        }
    }
}
