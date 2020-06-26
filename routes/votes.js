const express = require('express');
const router = express.Router();
const db = require('../config/database');
const Vote = require('../models/Vote');
const Answer = require('../models/Answer');

router.get('/', (req, res) => {
  res.send("INDEX VOTES");
});

router.post('/add', (req, res) => {

  Answer.findOne({ where: { title: req.body.answer_title, question_id: req.body.question_id } }).
    then(vote => {
      var voteData = {
        answer_id: vote.dataValues.id,
        user_id: req.body.user_id,
        question_id: req.body.question_id
      };
      Vote.create(voteData).then(res.send({ status: true }));
    });
});

module.exports = router;
