var fs = require('fs');

var lineReader = require('readline').createInterface({
        input: fs.createReadStream('events.json')
});
var out = fs.createWriteStream('newEvents.sql', { encoding: "utf8" });
var id = 1;
var playerId = 1;

lineReader.on('line', function(line) {
        var event = JSON.parse(line);
        out.write(parse(event, id));
        id++;
});

lineReader.on('error', function() {
        console.log('error happened');
});

lineReader.on('end', function() {
        console.log('closing up');
        out.end();
});

var parse = function(event, id) {
        delete event._id;
        event.eventTime = new Date(event.eventTime.$date).getTime();
        if(event.type == 'registerMatch'){
                event.match = {};
                event.match.team1 = event.team1;
                delete event.team1;
                event.match.team2 = event.team2;
                delete event.team2;
        } else if(event.type == 'createPlayer') {
                event.playerId = playerId++;
        }
        return 'INSERT INTO EVENTS (id, data) VALUES (' + id + ', \'' + JSON.stringify(event) + '\');\n';
};
