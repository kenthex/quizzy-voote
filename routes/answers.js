const express = require('express');
const router = express.Router();
const db = require('../config/database');
const Answers = require('../models/Answer');
const Votes = require('../models/Vote');

router.get('/', (req, res) => {
  res.send("INDEX ANSWERS");
});

router.get('/:question_id', (req, res) => {
  Answers.findAll({where: { question_id: req.params.question_id }}).then(answers => {
    answers.forEach(answer => {
      answer.dataValues.createdAt = answer.dataValues.title;
      delete answer.dataValues['title'];
    });
    
    res.send({ answers: answers });
  });
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
