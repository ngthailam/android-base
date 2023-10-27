## Android Base

A template/base for future Android project to reference

## Features

1. Clean architecture
Created with clean architecture in mind, with 3 layers: `presentation` - `domain` - `data`

1.1. Dependency
`presentation` depends on `domain`
`data` depends on `domain`

1.2. Responsibility
`domain` : Usecases, Business logic, Models
`data`: Repository, Datasources(Local, APIs, SDKs, etc), Data models
`presentation` : UI, UI logic, UI models
