const express = require('express');
const path = require('path');
const bodyParser = require('body-parser');

// Database
const db = require('./config/database');

// Test DB
db.authenticate().
  then( () => console.log('Database connected') ).
  catch( err => console.log('Error: ' + err) );


const app = express();
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

app.get('/', (req, res) => { res.send('INDEX'); });

// Users routes
app.use('/users', require('./routes/users'));
// Votes routes
app.use('/votes', require('./routes/votes'));
// Tokens routes
app.use('/tokens', require('./routes/tokens'));
// Questions routes
app.use('/questions', require('./routes/questions'));
// Answers routes
app.use('/answers', require('./routes/answers'));

const PORT = process.env.PORT || 3000;
app.listen(PORT, console.log(`SERVER STARTED ON PORT ${PORT}`));
db.sync();
