// @flow
import React, {Component} from 'react';
import { HashRouter as Router, Route, Link } from 'react-router-dom'
import { connect } from 'react-redux'
import type { State, List, Match } from '../types'
import ListPage from '../pages/ListPage.jsx'
import MatchesPage from '../pages/MatchesPage.jsx'
import {fetchList, fetchMatches} from '../actions';

const NewMatch = () => (
  <div>
    <Link to="/">Back</Link>
    <h1>New Match</h1>
  </div>
)

class App extends Component {


  componentDidMount() {
    const {dispatch} = this.props
    dispatch( fetchMatches )
    dispatch( fetchList )
  }

  render() {
   return <div style={{textAlign: 'center'}}>
     <Router>
       <div>
         <Route exact path='/' component={ListPage}/>
         <Route path='/matches' component={MatchesPage}/>
         <Route path='/newMatch' component={NewMatch}/>
       </div>
     </Router>
    </div>
  }

}

export default connect()(App);
