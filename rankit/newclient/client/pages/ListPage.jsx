import React, {Component} from 'react';
import type {State, List, Match} from '../types';
import {fetchList, fetchMatches} from '../actions';
import {List as RankList} from '../components/List.jsx';
import MatchList from '../components/MatchList.jsx';
import {connect} from 'react-redux';
import { Link } from 'react-router-dom'

class ListLoader extends Component{
  props: {
    list: List,
    matches: Array<Match>
  }

  render() {
    return <div>
            <div>
              <Link to="/newMatch">New Match</Link>
              <h1>Recent matches</h1>
              <MatchList list={this.props.list} matches={this.props.matches} numMatches='3'/>
              <Link to="/matches">more</Link>
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
