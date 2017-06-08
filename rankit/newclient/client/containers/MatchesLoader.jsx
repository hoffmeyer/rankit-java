import React, {Component} from 'react';
import type {State, Match} from '../types';
import {fetchMatches} from '../actions';
import MatchList from '../components/MatchList.jsx';
import { connect } from 'react-redux';

class MatchesLoader extends Component{
  props: {
    matches: Array<Match>
  }

  componentDidMount() {
    const {dispatch} = this.props
    dispatch( fetchMatches )
  }

  render() {
    return <MatchList matches={this.props.matches}/>
  }
}

function mapStateToProps(state) {
  const {matches} = state
  return {
    matches
  }
}

export default connect(mapStateToProps)(MatchesLoader);
