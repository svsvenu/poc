import React, { Component } from "react";

class Counter extends Component {
  state = {
    count: this.props.value,
    tags: ["tag1", "tag2", "tag4"]
  };

  handleIncrement = () => {
    console.log("Increment clicked");
    this.setState({ count: this.state.count + 1 });
  };

  render() {
    console.log(this.props);
    return (
      <React.Fragment>
        <span className="badge badge-primary m-2">{this.state.count}</span>
        <button
          onClick={this.handleIncrement}
          className="btn btn-secondary btn-sm"
        >
          Increment
        </button>
        <ul>
          {this.state.tags.map(tag => (
            <li key={tag}>{tag}</li>
          ))}
        </ul>
      </React.Fragment>
    );
  }
}

export default Counter;
