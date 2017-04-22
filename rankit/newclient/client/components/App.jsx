// @flow
import React from 'react';

type GreetProps = {
  greeting: number,
}

function Greet(props: GreetProps) {
  return (
    <h1>{props.greeting}</h1>
  )
}

export default class App extends React.Component {
  render() {
    return (
     <div style={{textAlign: 'center'}}>
       <Greet greeting="Hello Worlds" />
      </div>);
  }
}
