'use strict';

var React = require('react'),
    If = require('../helpers/If');

var AddPlayer = React.createClass({
    getInitialState: function(){
        return {
            nameAvailable: false,
            name: '',
            validationText: ''
        };
    },
    validate: function(name){
        name = name || this.state.name;
        if(typeof name !== 'undefined' && name !== ''){
            var matchingPlayers = this.props.players.filter(function(element){
                return element.name.toLowerCase() === name.toLowerCase();
            });
            if(matchingPlayers.length === 0){
                return true;
            }
        }
        return false;
    },
    handleClick: function(e){
        var self = this,
            data = {
            name: this.state.name
        };

        e.preventDefault();

        var req = new XMLHttpRequest();
        req.open('POST', '/api/player', true);
        req.setRequestHeader('Content-Type', 'application/json');
        req.onreadystatechange = function(){
            if( req.readyState === 4 && req.status === 200){
                self.props.updateList(JSON.parse(req.responseText));
                self.props.onNavigate('list');
            }
        };
        req.send(JSON.stringify(data));
    },
    onChange: function(e){
        this.setState({name: e.target.value});
        if(this.validate(e.target.value)){
            this.setState({validationText: 'Name available'});
        }
    },
    render: function(){
        return  <form className="form-horizontal">
            <h3>Add Player</h3>
            <div className="form-group">
                <label className="col-sm-2 control-label" htmlFor="playerName">Player name</label>
                <div className="col-sm-10">
                    <input 
                        value={this.state.name}
                        onChange={this.onChange}
                        className="form-control" 
                        type="text"
                    />
                    <If test={this.validate()}>
                        <span>The name is available</span>
                    </If>
                    <If test={!this.validate()}>
                        <span>Invalid name</span>
                    </If>
                </div>
            </div>
            <div className="form-group">
                <button 
                    disabled={!this.validate()} 
                    className="btn btn-primary pull-right" 
                    onClick={this.handleClick} 
                >Add</button>
            </div>
        </form>;
    }
});
    
module.exports = AddPlayer;
