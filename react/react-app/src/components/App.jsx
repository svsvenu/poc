import React, { Component } from "react";
import Movies from "./movies";
import Counters from "./counters";
import "bootstrap/dist/css/bootstrap.css";

class App extends Component {
  state = {};
  render() {
    return (
      <main className="container">
        <Counters />
      </main>
    );
  }
}

export default App;
