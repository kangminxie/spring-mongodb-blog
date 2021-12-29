import React from 'react';
import { Card } from 'react-bootstrap';
import { ChevronDownIcon, ChevronUpIcon } from '../../common/icon';

type Props = {
  title: string;
  bgColor?: string;
  children?: any;
  initialShow?: boolean;
};

type State = {
  show: boolean;
};

const INIT_STATE = {
  show: false,
};

export class MyToggleDisplay extends React.Component<Props, State> {
  state = { ...INIT_STATE };

  componentDidMount() {
    const { initialShow } = this.props;
    if (initialShow) {
      this.setState({
        show: true,
      });
    }
  }

  _toggleDisplayContent = () => {
    this.setState({
      show: !this.state.show,
    });
  };

  render() {
    const { show } = this.state;
    const { title, bgColor, children } = this.props;
    const bgStyle = bgColor ? bgColor : 'bg-dark';
    return (
      <div className='toggle-display'>
        <Card>
          <Card.Header
            className={`${bgStyle} text-white`}
            onClick={() => this._toggleDisplayContent()}
          >
            <span>{title}</span>
            <span className='float-right'>
              {show ? <ChevronUpIcon /> : <ChevronDownIcon />}
            </span>
          </Card.Header>
          {show && <Card.Body>{children}</Card.Body>}
        </Card>
      </div>
    );
  }
}
