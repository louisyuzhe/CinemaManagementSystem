package cinemaProject;
public class Movie {
	public int movieId ;
	private String title;
	private int runtime;
	private int year;
	private String rating;
	private String plot;
	private String poster;
	private String video;

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public Movie(int movieId, String title, int runtime, int year, String rating, String plot, String poster,
			String video) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.runtime = runtime;
		this.year = year;
		this.rating = rating;
		this.plot = plot;
		this.poster = poster;
		this.video = video;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	@Override
	public String toString() {

		return "Movie{" + "movieId:" + movieId + ", title:" + title + ", runtime:" + runtime + ", year:" + year + ", rating:" + rating + ", plot:" + plot + '}'+"\n";
	}

}