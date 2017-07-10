import React, {Component} from 'react';
import type {State, List, Match} from '../types';
import {fetchList, fetchMatches} from '../actions';
import {List as RankList} from '../components/List.jsx';
import MatchList from '../components/MatchList.jsx';
import Modal from '../components/Modal.jsx';
import {connect} from 'react-redux';
import { Link } from 'react-router-dom'

class ListLoader extends Component{
  props: {
    list: List,
    matches: Array<Match>
  }

  state: {
    isOpen: boolean
  }

  constructor(props) {
    super(props);
    this.state = {isOpen: false};
  }


  render() {
    const addMatch = () => {
      this.setState({
        isOpen: true
      })
    }
    const onClose = () => {
      console.log('onClose called')
      this.setState({
        isOpen: false
      });
    }
    return <div>
            <div>
              <h1>Recent matches</h1>
              <Modal onClose={onClose} isOpen={this.state.isOpen}><p>This is modal</p></Modal>
              <MatchList list={this.props.list} matches={this.props.matches} numMatches='3'/>
              <Link to="/matches" className='right' >more</Link>
              <button className='primary' onClick={addMatch}>Add Match</button>
            </div>
            <div>
              <h1>The list</h1>
              <RankList list={this.props.list}/>
            </div>
          </div>
  }
}

const mapStateToProps = (state: State) => {
  return state;
}

export default connect(mapStateToProps)(ListLoader);
