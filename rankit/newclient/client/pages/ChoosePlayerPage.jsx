import React, {Component} from 'react';
import {connect} from 'react-redux';
import {PlayerChooser} from '../components/PlayerChooser.jsx';

class ChoosePlayer extends Component{
  props: {
    list: List
  }

  render() {
    return <div>
      <h1>Who are you?</h1>
      <p>Choose your player</p>
      <PlayerChooser list={this.props.list}/>
      <p>If you are not in the list</p>
      <a href="#">Create new player</a>
    </div>
  }
}

const mapStateToProps = (state: State) => {
  return state;
}

export default connect(mapStateToProps)(ChoosePlayer);
