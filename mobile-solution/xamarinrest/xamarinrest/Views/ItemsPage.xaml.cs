using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

using xamarinrest.Models;
using xamarinrest.Views;
using xamarinrest.ViewModels;
using xamarinrest.Database;

namespace xamarinrest.Views
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class ItemsPage : ContentPage
	{
        private Subscription<Pessoa> listPessoaSubscription;

        ItemsViewModel viewModel;

        public ItemsPage()
        {
            InitializeComponent();
            BindingContext = viewModel = new ItemsViewModel();

            listPessoaSubscription = new Subscription<Pessoa>(async () => {
                ItemsListView.ItemsSource = await SQLiteRepository.Query<Pessoa>("SELECT * FROM " + typeof(Pessoa).Name);
            });
        }

        async void OnItemSelected(object sender, SelectedItemChangedEventArgs args)
        {
            var item = args.SelectedItem as Item;
            if (item == null)
                return;

            await Navigation.PushAsync(new ItemDetailPage(new ItemDetailViewModel(item)));

            // Manually deselect item.
            ItemsListView.SelectedItem = null;
        }

        async void AddItem_Clicked(object sender, EventArgs e)
        {
            await Navigation.PushModalAsync(new NavigationPage(new NewItemPage()));
        }

        protected override async void OnAppearing()
        {

            if (viewModel.Items.Count == 0)
                viewModel.LoadItemsCommand.Execute(null);

            ItemsListView.ItemsSource = await SQLiteRepository.Query<Pessoa>("SELECT * FROM " + typeof(Pessoa).Name);

            base.OnAppearing();
        }
    }
}