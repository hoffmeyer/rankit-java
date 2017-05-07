// @flow
import React from 'react'
import { HashRouter as Router, Route, Link } from 'react-router-dom'
import { connect } from 'react-redux'
import type { State } from '../types'
import ListLoader from '../containers/ListLoader.jsx'

type GreetProps = {
  greeting: string,
}

const Greet = (props: GreetProps) => (
    <h1>{props.greeting}</h1>
)

const Matches = () => (
  <h1>Matches</h1>
)

const App = (state) => (
   <div style={{textAlign: 'center'}}>
     <Router>
       <div>
         <ul>
           <li><Link to="/">List</Link></li>
           <li><Link to="/test">Matches</Link></li>
         </ul>

         <hr/>

         <Route exact path='/' component={ListLoader}/>
         <Route path='/test' component={Matches}/>
       </div>
     </Router>
    </div>
)

export default connect()(App);
