using xamarinrest.Configuration;
using xamarinrest.Database;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using System.Diagnostics;

namespace xamarinrest.Services.Rest
{
    class RestHolder<T>
    {
        private static readonly TimeSpan refreshTimeSpan = new TimeSpan( 0, 0, 5 );

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

        //Faz com que seja chamado o REST no endereço de SYNC a cada X segundos
        public static void StartAutoSync<T>() where T : new()
        {
            Task.Run(() => {
                requestRestAndSync<T>();
            });

            var holder = RestHolder<T>.instance;
            Device.StartTimer( refreshTimeSpan, () => {

                Task.Run(() => {
                    requestRestAndSync<T>();
                });

                return true; //restart timer
            });
        }

        //Requisita o endereço REST para Sincronizar a classe específica no banco SQLite local
        private static async void requestRestAndSync<T>() where T : new()
        {
            var holder = RestHolder<T>.instance;
            if (holder.LockThread)  return;
            holder.LockThread = true;

            try
            {
                //Pega o DateTime da ultima requisição desta Uri
                DateTime lastRequest = Prefs.getDateTime(holder.SyncUri);

                //Request e Sync
                long unixTimestamp = lastRequest.Ticks - new DateTime(1970, 1, 1).Ticks;

                Stopwatch stopwatch = new Stopwatch();
                StringBuilder log = new StringBuilder();

                stopwatch.Start();
                string content = await RestService.GetAsync(holder.SyncUri + (unixTimestamp / TimeSpan.TicksPerMillisecond));
                log = log.AppendFormat( "\n-------- REST REQUEST TIME <{1}> {0} ------- ", stopwatch.Elapsed, typeof(T).Name );

                stopwatch.Restart();
                List<T> list2 = JsonConvert.DeserializeObject<List<T>>(content);
                log = log.AppendFormat("\n-------- Deserialize TIME <{1}> {0} ------- ", stopwatch.Elapsed, typeof(T).Name );

                stopwatch.Restart();
                SQLiteRepository.Sync<T>(list2);
                log = log.AppendFormat("\n-------- SYNC TIME <{1}> {0} -------", stopwatch.Elapsed, typeof(T).Name );

                //Seta o DateTime da ultima requisição para AGORA
                Prefs.setDateTime(holder.SyncUri, DateTime.Now);
                holder.LockThread = false;

                Debug.WriteLine(log);
            }
            catch ( Exception e )
            {
                holder.LockThread = false;
                Console.WriteLine(e.Message);
            }
        }
    }
}
