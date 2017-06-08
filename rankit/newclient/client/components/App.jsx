// @flow
import React from 'react'
import { HashRouter as Router, Route, Link } from 'react-router-dom'
import { connect } from 'react-redux'
import type { State } from '../types'
import ListLoader from '../containers/ListLoader.jsx'
import MatchesLoader from '../containers/MatchesLoader.jsx'

const NewPlayer = () => (
  <h1>New Player</h1>
)

const App = (state: State) => (
   <div style={{textAlign: 'center'}}>
     <Router>
       <div>
         <ul>
           <li><Link to="/">List</Link></li>
           <li><Link to="/matches">Matches</Link></li>
           <li><Link to="/addPlayer">New Player</Link></li>
         </ul>
         <Route exact path='/' component={ListLoader}/>
         <Route path='/matches' component={MatchesLoader}/>
         <Route path='/addPlayer' component={NewPlayer}/>
       </div>
     </Router>
    </div>
  )

export default connect()(App);
