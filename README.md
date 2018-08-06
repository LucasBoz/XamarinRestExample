# Xamarin Rest Example

Simple WebServer and Mobile App using Rest with abilities to SYNC data

## Mobile instructions
All configs can be defined in Init methods on each class:

* RestService.cs
* SQLiteRepository.cs
* SyncService.cs

These methods should be called at the initalization of the app, at `App.xaml.cs`

```
protected override void OnStart ()
{
  // Handle when your app starts
  SQLiteRepository.Init();
  RestService.Init();
  SyncService.Init();
}
```

### Configuring SQLiteRepository

Define database manipulation before app starts at `SQLiteRepository.cs` Init method

```
public static void Init()
{
    db.CreateTable<Pessoa>();
    db.CreateTable<Empresa>();
}
```

### Configuring RestService

Define Rest Uri's that Sync mecanism will be calling at `RestService.cs` Init method

```
var pessoaHolder = new RestHolder<Pessoa> {
    SyncUri = "pessoa/sync?date=",
    SyncDeletedUri = "pessoa/syncDeleted?date=",
    InsertUri = "pessoa/insert",
    UpdateUri = "pessoa/update",
    DeleteUri = "pessoa/delete/{0}"
};

var empresaHolder = new RestHolder<Empresa> {
    SyncUri = "empresa/sync?date=",
    InsertUri = "empresa/insert",
    UpdateUri = "empresa/update",
    DeleteUri = "empresa/delete/{0}"
};
```

`Uri's is not required, but will have a default value defined by <T> class name + method rest name. Ex: <entity>/insert`

`Delete Uri's must have arg0 ID. Ex: <entity>/delete/{0}`


### Configuring SyncService
Define Sync features that can sync data periodically at `SyncService.cs` Init method.

*AutoSync
*AutoSyncDeleted

```
public static void Init()
{
    SyncService.StartAutoSync<Pessoa>();
    SyncService.StartAutoSync<Empresa>();

    SyncService.StartAutoSyncDeleted<Pessoa>();
}
```

`StartAutoSync creates a timer that execute a rest call to SyncUri and autoSync in background`

`StartAutoSyncDeleted creates a timer that execute a rest call to SyncDeletedUri and autoSync deleted entities in background`

### Watcher functionality (Subscribe)
It is possible to create a watcher, that will execute a block of code by subscribing


