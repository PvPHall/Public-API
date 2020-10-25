## PvPHall Client API

This is the official repository for the Client API of PvPHall Client. Can be used to restrict access/mods, perform emotes, check if using AC and more.

### Table of content
- [Usage](#usage)
  - [For server owners](#for-server-owners)
    - [Installing](#installing)
    - [Configuring](#configuring)
  - [For developers](#for-developers)
    - [Setup](#setup)
    - [Compiling](#compiling)
- [Configuration guide](#configuration-guide)
- [API guide](#api-guide)
- [Contact](#contact)

### Usage

#### For server owners

##### Installing
1) Download the latest jar from [releases](https://github.com/PvPHall/Public-API/releases)
2) Download [ProtocolLib](https://dev.bukkit.org/projects/protocollib) plugin
2) Put them in your plugins folder
3) Restart your server and configure the plugin to your liking

##### Configuring
1) Open the `config.yml` located in `plugins/HallClientAPI`
2) Make your changes. All the options are explained below.

#### For developers

##### Setup
1) Clone this repository to your local dev environment
2) Open your favorite IDE and import the project as a `Maven project`
3) Download the dependencies from `pom.xml` with your IDE

##### Compiling
1) Run `mvn clean install` or build it with your IDE as `clean install` for Maven goal
2) The jar output in the `../plugins` folder

### Configuration guide
| Key | Type | Description |
|-----|------|-------------|
| OPTIONS.RICH_PRESENCE_ENABLED | boolean | If the config RichPresence should be used
| OPTIONS.RICH_PRESENCE_TEXT | string | The RichPresence text to set for all the players
| CONNECTION.ALLOW_ONLY_HALLCLIENT | boolean | If you restrict access for only PvPHall Client users
| CONNECTION.ALLOW_ONLY_AC | boolean | If you restrict access for only PvPHall AC enabled

### API guide
To access all the available methods, get the instance:
```java
HallAPI.getInstance()
```
This will return you a `IHallAPI` interface. This will list you all the methods you can use.

### Contact
If you need help or have any suggestions, feel free to contact me on:
- Twitter: @QuiiBzDev
- Discord: Tom - QuiiBz#7533