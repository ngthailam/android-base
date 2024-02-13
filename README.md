# Android Base

A basic base/template for a Android project.
Supported features:
| Feature     | Description | PR link     |
| :---        |    :----  |          :---: |
| Structure      |   Multi module with clean architecture     | NA   |
| Multi flavor  | Multiple flavours with different configs per flavour |  [Link](https://github.com/ngthailam/android-base/pull/24)|
| DI  | Dependency injection with Koin, with example to inject different class per condition(s) like Android version |  |
| Room DB  | Room database with coroutine |  [Link](https://github.com/ngthailam/android-base/pull/13)|
| Datastore  | Android new Datastore with protobuf |  [Link](https://github.com/ngthailam/android-base/pull/27)|
| Retrofit  | API calls + Interceptors |  [Link](https://github.com/ngthailam/android-base/pull/15)|
| Code format   | ktlint |  [Link](https://github.com/ngthailam/android-base/pull/34)|
| CI   | Simple build process with code analysis |  [Link](https://github.com/ngthailam/android-base/pull/29)|
| PR Template  | Simple template for Pull requests |  [Link](https://github.com/ngthailam/android-base/pull/31)|
| Git hooks  | Local githooks + git commit check in CI |  NA |

---

## Architecture
### Overview

Use `Clean Architecture` with 3 layers `Presentation`, `Domain`, `Data`.
`Presentation` depends on Domain
`Domain` is standable (no Android code if possible), call to Data via Interface
`Data` depends on Domain
This project contains and `app` layer for Android specific initializations + Dependency injections

#### Presentation
UI + UI logic such as XML, Composable, Viewmodels, Navigations, Deeplink, Notifications, etc.
UiModel + UiModelMapper for greater control + logic separation for cases 2 screens uses the same domain model.
#### Domain
Usecases which contains business logic (Can skip if it only contains 1 get call to data layer)
Models
#### Data
External services, databases, API calls.
Entity + EntityMapper to domain Models

### Error Handling
(TBD)

---
## Useful commands
### Test reports
(TBD)
### Code formatters
This project use ktlint for code formatting.
```
./gradlew ktlintCheck
```

```
./gradlew ktlintFormat
```

### Current commit-msg git hook
Used to force commit messages to follow a given pattern. Update REGEX based on your usage, then put this block inside git hook using `nano .git/hooks/commit-msg`.
```
MSG_FILE=$1
FILE_CONTENT="$(cat $MSG_FILE)"
REGEX="^(feat|chore|test|refactor|fix|enhance)\([0-9]+\):.*"
ERROR_MSG="Commit message format must match regex \"${REGEX}\""
if [[ $FILE_CONTENT =~ $REGEX ]]; then
 echo "Nice commit!"
else
  echo "Bad commit \"$FILE_CONTENT\", check format."
 echo $ERROR_MSG
 exit 1
fi

```
