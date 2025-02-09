import React, { Component } from "react";
import { getMovies } from "../services/fakeMovieService";
import { Pagination } from "../common/pagination";
import Like from "../common/Like";

class Movies extends Component {
  componentDidMount() {
    console.log("mounted");
  }
  render() {
    console.log(this.props.onDelete);
    return (
      <React.Fragment>
        <table className="table">
          <thead>
            <tr>
              <th>Movie</th>
              <th>Genre</th>
              <th>Stock</th>
              <th>Rate</th>
              <th>Like</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            {this.props.movies.map(movie => (
              <tr key={movie._id}>
                <td>{movie.title}</td>
                <td>{movie.genre.name}</td>
                <td>{movie.numberInStock}</td>
                <td>{movie.dailyRentalRate}</td>
                <td>
                  <Like />
                </td>
                <td>
                  <button
                    className="btn btn-danger btn-sm"
                    onClick={() => this.props.onDelete(movie)}
                  >
                    delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </React.Fragment>
    );
  }
}

export default Movies;
