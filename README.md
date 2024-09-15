# 🫡 Base Requirements


- [x] Bottom Nav > Jobs and Bookmarks ✅
- [x] Jobs Screeen ✅
    - [x] Paginated Data from the Endpoint  |  **ASSUMPTION : No persistence necessary**
    - [x] Each card > Title, Location, Salary, Phone Data 
    - [x] Clicking on a card > Card Screen 
- [x] Bookmarks Screen✅
    - [x] Persisted on Local Storage  |  **ASSUMPTION : No Updates / Invalidation**


---

# 🌟 Additional Features
Due to time constraints, many additonal key features could not be added within the time limit:

- [x] *Cleaner* Clean Architecture ✅
- [x] Better separation of Domain and Data Layer ✅

  Might be overkill for a small project, but enhances code readability and testability

- [ ] Page Loading Indicator

  When reaching the eend of the page fetched from the paging source, there is a delay between in retreving the next page. A progreess indicator would indicate that the end of the list hasn't been reached yet.

- [ ] Retry Snackbar on Connection Failure

  Currently there is no way to retry when the pagingsource fails to load a page due to say, network issues.

- [ ] Connection Lost Snackbar

  There is no indication when the internet connection is lost. Bad UX

- [ ] Improved Descriptions Page

  Languages Suppoort, Display Media, Better formatting of content

- [ ] Category Icons

  Currently the same Icon is being used accross all job categories. A simple enum could be used to assign different icons to different categories by their id.

- [ ] Separate `Job` Models for UI and Domain Layer
- [ ] Propper Dependency Injection with Dagger Hilt
- [ ] Dark Theme
- [ ] Hardcoded Dimension and Color Values


---
#  ⭐ Key Architecture Features

- UI built with Jetpack Compose.
- A single-activity architecture with Compose Navigation.
- A presentation layer that contains several Compose Screens and a ViewModel.
- Reactive UIs using Flow and coroutines for asynchronous operations.
- Follows Uni-Directional Data Flow Principles
- A data layer with a repository and two data sources (local using Room and remote using the provided test api).


## 🔍 Decisions


1. Simple `JobsModule` insttead of Dagger-Hilt Dependency Injection. Quicker Build and Development times
2. Initially I made the deliberate choice to abandon Clean Architecture principles in architecting the project to:

    a. Avoid over-complicating the code

    b. Deliver the project on time. 

3. However since necessary changes have been made to comply further with clean architecture and official guidelines by refactoring the package structure, naming conventions etc.
4. Type Safe Navigation with Safe Args wasn't opted for, since it involved wrestling with Android Studio and gradle to get the correct dependencies installed.

# ✨ Showcase

| ![](https://i.imgur.com/JbePGrV.png)<br><br> | ![](https://i.imgur.com/njYV3Gi.png)<br> |
| -------------------------------------------- | ---------------------------------------- |
| ![](https://i.imgur.com/me2nAQS.jpeg)<br>    |                                          |
| <br>                                         |                                          |

## Video

https://github.com/user-attachments/assets/1858ce88-408c-46bd-8425-478c79d3d5e6



# 📃 Development Journey 
- [x] Fimga Designs ~ 6hrs
    - [x] JobCard
    - [x] Home Screen
    - [x] Bookmark Screen
    - [x] Details Screen
    - [x] Theming and Styling
- [x] Replicating UI Components in Android Studio  ~ 4-6hrs
    - [x] JobCard
    - [x] CardList
    - [x] HomeScreen
    - [x] BookmarkScreen
    - [x] DetailsScreen
- [x] Functional UI Components  ~ 2hrs
    - [x] Navigation
- [x] Job API and Fitting it all together~ 2-3 hrs
    - [x] Retrofit API
    - [x] Writing tests to parse Job from response.json
    - [x] Handling of edge cases
    - [x] Fitting it all together
- [x] Persistence and Usecases ~ 30 mins
- [x] Error and Loading State Handling  ~ 30 mins
- [x] Misc ~ 2-3 hrs


# 📦 Assets
![|350](https://i.imgur.com/G8mKcgJ.jpeg)

- [Figma file](docs/LokalJobDesign.fig)

## 💭 Thoughts
- Should've used git to initialise repo and made changes. Git is a better proof of process
- Took longer designing and implementing the designs than initially anticipated. 
- Designing components form scratch is time consuming
- Other time consuming factors
    - Trying new apis that require wrestling with gradle
    - Pexel perfecting designs and implementation 
    - Optimisations
