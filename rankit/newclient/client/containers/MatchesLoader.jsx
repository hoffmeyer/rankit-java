import React, {Component} from 'react';
import type {State, Match} from '../types';
import {fetchMatches, fetchList} from '../actions';
import MatchList from '../components/MatchList.jsx';
import { connect } from 'react-redux';

class MatchesLoader extends Component{
  props: {
    matches: Array<Match>
  }

  componentDidMount() {
    const {dispatch} = this.props
    dispatch( fetchMatches )
    dispatch( fetchList )
  }

  render() {
    return <MatchList list={this.props.list} matches={this.props.matches}/>
  }
}

function mapStateToProps(state: State) {
  return state
}

export default connect(mapStateToProps)(MatchesLoader);
