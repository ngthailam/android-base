## Android Base

A template/base for future Android project to reference


## Features

1. Clean architecture
Created with clean architecture in mind, with 3 layers: `app` - `domain` - `data`

1.1. Dependency
`app` depends on `domain`
`data` depends on `domain`

1.2. Responsibility
`domain` : Usecases, Business logic, Models
`data`: Repository, Datasources(Local, APIs, SDKs, etc), Data models 
`app` : UI, UI logic, UI models
