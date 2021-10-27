# MovieCorner

[![adityaids](https://circleci.com/gh/adityaids/MovieCorner.svg?style=svg)](https://circleci.com/gh/adityaids/MovieCorner)

![MovieCorner Preview](https://github.com/adityaids/MovieCorner/blob/master/1.gif width="50")

![MovieCorner Preview](https://github.com/adityaids/MovieCorner/blob/master/2.gif width="50")

Simple project using TMDB API to study and play with some android components, architecture and tools for Android development.

## Tech Stack

This project uses [feature modularization architecture](https://proandroiddev.com/intro-to-app-modularization-42411e4c421e).
The movie corner app uses MVVM as software design pattern

## Development setup

You require the latest Android Studio Arctic Fox 2020.3.1 patch 3 (stable channel) to be able to build the app.

### Libraries

- Application entirely written in [Kotlin](https://kotlinlang.org)
- Asynchronous processing using [Coroutines](https://kotlin.github.io/kotlinx.coroutines/)
- Uses [Koin](https://github.com/InsertKoinIO/koin) for dependency injection
- Uses [Paging2](https://developer.android.com/topic/libraries/architecture/paging)
- Uses [Retrofit](https://square.github.io/retrofit)
- Uses [ExoPlayer](https://exoplayer.dev)

### API keys

You need to supply API / client keys for the service the app uses.

- [TMDb](https://developers.themoviedb.org)

Once you obtain the key, you can set them in your `~/local.properties`:

### Issue

still lot of issue and i try to fix it:
- Deprecated PagedList need update to paging v3
- Not Implement Paging for unlimitted data request for movie and review
- Select Movie by genre not show related information
- PagingListAdapter show data slowly

fitur done:
- showing list of movie
- request movie by genre
- get Movie trailer and show to ExoPlayer
- show detail info for movie
- show review for movie
