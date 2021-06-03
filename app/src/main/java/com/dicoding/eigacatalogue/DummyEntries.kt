package com.dicoding.eigacatalogue

import com.dicoding.eigacatalogue.source.remote.response.CreatedBy
import com.dicoding.eigacatalogue.source.remote.response.DetailMovieResponse
import com.dicoding.eigacatalogue.source.remote.response.Genres


object  DummyEntries {
    fun getMovieDummy(): ArrayList<MovieEntity> {
        val movies = ArrayList<MovieEntity>()
        movies.add(
                MovieEntity(
                        id = 550,
                        title = "Fight Club",
                        tagLine = "How much can you know about yourself if you've never been in a fight?",
                        release = "1999-10-12",
                        overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
                        image = "https://image.tmdb.org/t/p/w500/fCayJrkfRaCRCTh8GqN30f8oyQF.jpg",
                        score = 0.5,
                        genre = "Drama",
                        creator = "-"
                )
        )
        return movies
    }

    fun getTVShowDummy(): ArrayList<MovieEntity> {
        val tvShows = ArrayList<MovieEntity>()
        tvShows.add(
                MovieEntity(
                        id = 1399,
                        title = "Game of Thrones",
                        tagLine = "Winter Is Coming",
                        release = "2011-04-17",
                        overview = "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                        image = "https://image.tmdb.org/t/p/w500//u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                        score = 369.594,
                        genre = "Sci-Fi & Fantasy, Drama, Action & Adventure, Mystery",
                        creator = "David Benioff, D. B. Weiss"
                )
        )
        return tvShows
    }

    fun getRemoteMovieDummy(): ArrayList<DetailMovieResponse> {
        val genres = ArrayList<Genres>()
        genres.add(Genres("Drama"))

        val createdBy = ArrayList<CreatedBy>()
        createdBy.add(CreatedBy("-"))

        val movies = ArrayList<DetailMovieResponse>()
        movies.add(
            DetailMovieResponse(
                id = 550,
                title = "Fight Club",
                tagLine = "How much can you know about yourself if you've never been in a fight?",
                release = "1999-10-12",
                overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
                image = "https://image.tmdb.org/t/p/w500/fCayJrkfRaCRCTh8GqN30f8oyQF.jpg",
                score = 0.5,
                genres = genres,
                createdBy = createdBy
            )
        )
        return movies
    }

    fun getRemoteTVShowDummy(): ArrayList<DetailMovieResponse> {
        val genres = ArrayList<Genres>()
        genres.add(Genres("Sci-Fi & Fantasy"))
        genres.add(Genres("Drama"))
        genres.add(Genres("Action & Adventure"))
        genres.add(Genres("Mystery"))

        val createdBy = ArrayList<CreatedBy>()
        createdBy.add(CreatedBy("David Benioff, D. B. Weiss"))

        val tvShows = ArrayList<DetailMovieResponse>()
        tvShows.add(
            DetailMovieResponse(
                id = 1399,
                title = "",
                tagLine = "Winter Is Coming",
                release = "2011-04-17",
                overview = "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                image = "https://image.tmdb.org/t/p/w500//u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                score = 369.594,
                genres = genres,
                createdBy = createdBy
            )
        )
        return tvShows
    }
}