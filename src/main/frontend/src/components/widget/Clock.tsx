import React from 'react';

type State = {
  time: string;
};

class Clock extends React.Component<any, State> {
  state = {
    time: new Date().toLocaleTimeString(),
  };

  private timer: any | undefined;

  componentDidMount() {
    this.timer = setInterval(() => this.updateTime(), 1000);
  }

  componentWillUnmount() {
    clearInterval(this.timer);
  }

  updateTime() {
    this.setState({
      time: new Date().toLocaleTimeString(),
    });
  }

  render() {
    return (
      <span className='my-3 text-center'>
        Current local time is: {this.state.time}
      </span>
    );
  }
}

export default Clock;
