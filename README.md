# quickorder
# How to run
    - Open the project using Android Studio (Meerkat or newer)
    - Let Gradle sync all dependencies
    - Run the app on an Android emulator or physical device (API 26+)
    - The app will launch directly to the Quick Order screen

# Framework & State Management
    - Language: Kotlin
    - UI Framework: Jetpack Compose (Material 3)
    - Architecture: MVVM
    - State Management:
        - ViewModel as the single source of truth
        - StateFlow to expose UI state
        - UI observes state via collectAsStateWithLifecycle()
    - Data Source: Local in-memory repository (hardcoded sample data)
=> The UI is fully state-driven: Compose renders based on immutable UiState, and all business logic is handled inside the ViewModel.

# Trade-offs & Improvements
- Given more time, I would consider:
    - Adding unit tests for the ViewModel (cart logic, filtering, totals)
    - Improving UX with animations (e.g., quantity changes, empty state)
    - Introducing dependency injection (e.g., Hilt) for better scalability
    - Supporting configuration changes & state persistence (e.g., SavedStateHandle)
    - Refining UI polish and accessibility (talkback, content descriptions)
