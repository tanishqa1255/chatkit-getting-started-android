const Chatkit = require("@pusher/chatkit-server")

const chatkit = new Chatkit.default({
  instanceLocator: "v1:us1:2641cd1b-0229-4958-a7d6-d1d3527fab55",
  key:
    "46e610c9-9f6d-44c9-9852-462571289772:Y71AQamGMnf6K2KuM+bKilUtMqaYOrL/omrBPcexOlQ="
})

module.exports = chatkit
