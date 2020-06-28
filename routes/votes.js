const express = require('express');
const router = express.Router();
const db = require('../config/database');
const Vote = require('../models/Vote');
const Answer = require('../models/Answer');

router.get('/', (req, res) => {
  res.send("INDEX VOTES");
});

router.get('/check/:user_id/:question_id', (req, res) => {
  Vote.findOne(
    { where: { user_id: req.params.user_id, question_id: req.params.question_id },

    include: [ {
      model: Answer,
      association: 'answer_data',
      foreignKey: 'id',
      attributes: ['title']
    }
    ]}
    ).then(vote => {
      console.log("Приходит: USER_ID - " + req.params.user_id );
      console.log("Приходит: QUESTION_ID - " + req.params.question_id);
    if(vote) {
      res.send({ status: 'voted', title: vote.dataValues.answer_data.dataValues.title });
    } else { res.send({ status: 'not voted' }); }

  });
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
