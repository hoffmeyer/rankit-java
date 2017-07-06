import React, {Component} from 'react';
import type {State, Match} from '../types';
import {fetchMatches, fetchList} from '../actions';
import MatchList from '../components/MatchList.jsx';
import {connect} from 'react-redux';
import { Link } from 'react-router-dom'

class MatchesLoader extends Component{
  props: {
    matches: Array<Match>
  }

  render() {
    return <div>
            <Link to="/" className='left'>Back</Link>
            <h1>Matches</h1>
            <MatchList list={this.props.list} matches={this.props.matches} numMatches='100'/>
          </div>;
  }
}

function mapStateToProps(state: State) {
  return state
}

export default connect(mapStateToProps)(MatchesLoader);
