// @flow
import React from 'react';
import { HashRouter as Router, Route, Link } from 'react-router-dom'

type GreetProps = {
  greeting: string,
}

const Greet = (props: GreetProps) => (
    <h1>{props.greeting}</h1>
)

const Thing = () => (
  <h2>Thing</h2>
)

const Thang = () => (
  <h2>THANG!</h2>
)

const App = () => (
   <div style={{textAlign: 'center'}}>
     <Greet greeting="Hello Worlds" />
     <Router>
       <div>
         <ul>
           <li><Link to="/">Thing</Link></li>
           <li><Link to="/test">Thang</Link></li>
         </ul>

         <hr/>

         <Route exact path='/' component={Thing}/>
         <Route path='/test' component={Thang}/>
       </div>
     </Router>
    </div>
)

export default App
