const express = require('express');
const path = require('path');
const bodyParser = require('body-parser');
const axios = require('axios').default;

// Database
const db = require('./config/database');

// Test DB
db.authenticate().
  then( () => console.log('Database connected') ).
  catch( err => console.log('Error: ' + err) );

const app = express();
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

app.get('/', (req, res) => {
  res.sendFile(__dirname + '/web/index.html');
});

app.get('/admin', (req, res) => {
  res.sendFile(__dirname + '/web/admin.html');
});
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

app.get('*', (req, res) => {
  res.send('404');
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, console.log(`SERVER STARTED ON PORT ${PORT}`));
db.sync({ force: false });
