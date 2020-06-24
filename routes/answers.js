const express = require('express');
const router = express.Router();
const db = require('../config/database');
const Answers = require('../models/Answer');

router.get('/', (req, res) => {
  res.send("INDEX ANSWERS");
});

router.post('/add', (req, res) => {
  console.log(req.body.title.length);

  var mas = req.body.title;
  var id = req.body.id;

  for(var i = 0; i < mas.length; i++) {
    req.body.title = mas[i];
    Answers.create(req.body).
      then(answer => res.send({ result: "created: " + answer.title })).
      catch(err => console.log(err));
  }

});

module.exports = router;
