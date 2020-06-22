var colorReset = "\x1b[0m";
var colorGreen = "\x1b[32m";
var colorRed = "\x1b[31m";

exports.success = (param, result) => {
  console.log( colorGreen + "+ " + param + ": " + result + colorReset );
}

exports.failure = (param, result) => {
  console.log( colorRed + "- " + param + ": " + result + colorReset );
}

exports.warning = (param, result) => {}
