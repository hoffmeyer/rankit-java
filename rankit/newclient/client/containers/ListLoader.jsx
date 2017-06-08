import React, {Component} from 'react';
import type {State, List} from '../types';
import {fetchList} from '../actions';
import {List as RankList} from '../components/List.jsx';
import { connect } from 'react-redux';

class ListLoader extends Component{
  props: {
    list: List
  }

  componentDidMount() {
    const {dispatch} = this.props
    dispatch( fetchList )
  }

  render() {
    return <RankList list={this.props.list}/>
  }
}

const mapStateToProps = state => {
  const {list} = state
  return {
    list
  }
}

export default connect(mapStateToProps)(ListLoader);
