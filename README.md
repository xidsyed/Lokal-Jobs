## Table of Contents
1. [Base Requirements](#-base-requirements)
2. [Additional Features](#-additional-features)
3. [Key Architecture Features](#-key-architecture-features)
4. [Decisions](#-decisions)
5. [Showcase](#-showcase)
6. [Video](#-video)
7. [Development Journey](#-development-journey)
8. [Design Assets](#-design-assets)
9. [Thoughts](#-thoughts)

<br><br>
# 🫡 Base Requirements


- [x] Bottom Nav > Jobs and Bookmarks ✅
- [x] Jobs Screeen ✅
    - [x] Paginated Data from the Endpoint  |  **ASSUMPTION : No persistence necessary**
    - [x] Each card > Title, Location, Salary, Phone Data 
    - [x] Clicking on a card > Card Screen 
- [x] Bookmarks Screen✅
    - [x] Persisted on Local Storage  |  **ASSUMPTION : No Updates / Invalidation**


<br><br>

# 🌟 Additional Features
Due to time constraints, many additonal key features could not be added within the time limit:

- [x] *Cleaner* Clean Architecture ✅ [9926cd2](https://github.com/xidsyed/Lokal-Jobs/commit/9926cd29abcc4ad314bb5d2b4f3cc42d85b616f5)
- [x] Better separation of Domain and Data Layer ✅ [e370f44](https://github.com/xidsyed/Lokal-Jobs/commit/e370f44a248c2beac4a8068eaa124915befceff3)

  Might be overkill for a small project, but enhances code readability and testability

- [X] Page Loading Indicator ✅ [#3](https://github.com/xidsyed/Lokal-Jobs/issues/3)

  When reaching the eend of the page fetched from the paging source, there is a delay between in retreving the next page. A progreess indicator would indicate that the end of the list hasn't been reached yet.

- [X] Retry Snackbar on Connection Failure ✅ [#8](https://github.com/xidsyed/Lokal-Jobs/pull/8)

  Currently there is no way to retry when the pagingsource fails to load a page due to say, network issues.

- [x] Connection Lost Indicator ✅[#12](https://github.com/xidsyed/Lokal-Jobs/pull/12)

  There is no indication when the internet connection is lost. Bad UX

- [x] Improved Descriptions Page ✅ [#15](https://github.com/xidsyed/Lokal-Jobs/pull/15)

  Languages Suppoort, Display Media, Better formatting of content

- [ ] Navigation and Interaction animations (under progress) 

- [ ] Category Icons

  Currently the same Icon is being used accross all job categories. A simple enum could be used to assign different icons to different categories by their id.

- [ ] Separate `Job` Models for UI and Domain Layer
- [ ] Propper Dependency Injection with Dagger Hilt
- [ ] Dark Theme
- [ ] Hardcoded Dimension and Color Values

<br><br>


#  ⭐ Key Architecture Features

- UI built with Jetpack Compose.
- A single-activity architecture with Compose Navigation.
- A presentation layer that contains several Compose Screens and a ViewModel based on the MVVM design pattern.
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

<br><br>
# ✨ Showcase

| ![](https://i.imgur.com/GP8Iewj.jpeg)<br><br> | ![](https://i.imgur.com/p7nu7us.jpeg)<br> |
| -------------------------------------------- | ---------------------------------------- |
| ![](https://i.imgur.com/aM3NhzI.jpeg)<br>    | ![](https://i.imgur.com/Thx8DGV.png)                                        |
| <br>                                         |                                          |

<br><br>
## 🎥 Video


https://github.com/user-attachments/assets/63786430-9fdb-4db8-b0cd-eb731f1461a8




<br><br>
# 📃 Development Journey
This assignment was developed and submitted in 2 days time. I followed the action plan below:
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

<br><br>
# 📦 Design Assets
#### [Figma file](docs/LokalJobDesign.fig)
![image|350](https://github.com/user-attachments/assets/a6b9988d-8ee0-4eac-b803-d454f75b6b16)
![image|350](https://github.com/user-attachments/assets/deaaba5d-0bd0-4409-92b7-f344054abd10)


<br><br>
## 💭 Thoughts
- Should've used git to initialise repo and made changes. Git is a better proof of process
- Took longer designing and implementing the designs than initially anticipated. 
- Designing components form scratch is time consuming
- Other time consuming factors
    - Trying new apis that require wrestling with gradle
    - Pexel perfecting designs and implementation 
    - Optimisations
