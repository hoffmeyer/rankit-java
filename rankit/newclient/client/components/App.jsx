// @flow
import React from 'react'
import { HashRouter as Router, Route, Link } from 'react-router-dom'
import { connect } from 'react-redux'
import { increment } from '../actions'
import type { State } from '../types'

type GreetProps = {
  greeting: string,
}

const Greet = (props: GreetProps) => (
    <h1>{props.greeting}</h1>
)

const mapStateToProps = (state: State) => {
  return {
    count: state.count
  }
}

const List = () => (
  <h2>The list</h2>
)

const Matches = () => (
  <h2>Matches</h2>
)

const App = (state) => (
   <div style={{textAlign: 'center'}}>
     <Greet greeting="Hello Worlds" />
     <Router>
       <div>
         <button onClick={ e => {state.dispatch(increment())}}>increment</button>
         <p>{state.count}</p>
         <ul>
           <li><Link to="/">List</Link></li>
           <li><Link to="/test">Matches</Link></li>
         </ul>

         <hr/>

         <Route exact path='/' component={List}/>
         <Route path='/test' component={Matches}/>
       </div>
     </Router>
    </div>
)

export default connect(mapStateToProps)(App);
