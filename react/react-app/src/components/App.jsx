import React, { Component } from "react";
import Movies from "./movies";
import Counters from "./counters";
import "bootstrap/dist/css/bootstrap.css";
import { getMovies } from "../services/fakeMovieService";
import "font-awesome/css/font-awesome.css";

class App extends Component {
  state = { movies: getMovies() };

  handleDelete = movie => {
    console.log(movie);
    const movies = this.state.movies.filter(m => {
      return m._id !== movie._id;
    });
    this.setState({ movies: movies });
  };

  render() {
    return (
      <main className="container">
        <Movies movies={this.state.movies} onDelete={this.handleDelete} />
      </main>
    );
  }
}

export default App;
