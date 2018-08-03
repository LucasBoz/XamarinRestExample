using System;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using xamarinrest.Models;

namespace xamarinrest.Views
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class EmpresasPage : ContentPage
    {
        public ObservableCollection<Empresa> EmpresasList { get; set; }

        public EmpresasPage()
        {
            InitializeComponent();

            EmpresasList = new ObservableCollection<Empresa>
            {
                new Empresa{ Nome = "EITS", Cnpj = "12313123" },
                new Empresa{ Nome = "EITS2", Cnpj = "12313123" },
                new Empresa{ Nome = "EITS3", Cnpj = "12313123" },
                new Empresa{ Nome = "EITS4", Cnpj = "12313123" },
            };
			
			MyListView.ItemsSource = EmpresasList;
        }

        async void Handle_ItemTapped(object sender, ItemTappedEventArgs e)
        {
            if (e.Item == null)
                return;

            await DisplayAlert("Item Tapped", "An item was tapped.", "OK");

            //Deselect Item
            ((ListView)sender).SelectedItem = null;
        }
    }
}
