import React, { Component } from "react";
import Counter from "./counter";

class Counters extends Component {
  state = {
    counters: [{ id: "counter1", count: 1 }, { id: "counter2", count: 2 }]
  };

  handleDelete = () => {
    console.log("delete called");
  };
  render() {
    return (
      <div>
        {this.state.counters.map(mov => {
          return (
            <Counter
              key={mov.id}
              value={mov.count}
              onDelete={this.handleDelete}
            />
          );
        })}
        <br />
      </div>
    );
  }
}

export default Counters;
