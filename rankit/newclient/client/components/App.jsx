// @flow
import React, {Component} from 'react';
import { HashRouter as Router, Route, Link, Redirect } from 'react-router-dom'
import { connect } from 'react-redux'
import type { State, List, Match } from '../types'
import ListPage from '../pages/ListPage.jsx'
import MatchesPage from '../pages/MatchesPage.jsx'
import {fetchList, fetchMatches} from '../actions';

const ChoosePlayer = () => (
  <h1>Choose player</h1>
)

const PlayerStats = () => (
  <h1>Player stats</h1>
)

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
         <Route exact path='/choosePlayer' component={ChoosePlayer}/>
         <CookieRoute exact path='/' component={ListPage}/>
         <CookieRoute path='/matches' component={MatchesPage}/>
         <CookieRoute path='/newMatch' component={NewMatch}/>
       </div>
     </Router>
    </div>
  }
}

const CookieRoute = ({ component: Component, ...rest }) => (
  <Route {...rest} render={props => (
    true ? (
      <Component {...props}/>
    ) : (
      <Redirect to={{
        pathname: '/choosePlayer',
        state: { from: props.location }
      }}/>
    )
  )}/>
)

export default connect()(App);
