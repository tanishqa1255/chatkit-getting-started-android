const Chatkit = require("@pusher/chatkit-server")

const chatkit = new Chatkit.default({
  instanceLocator: "v1:us1:98bbf1de-0845-41c1-a30b-7faf6ba97898",
  key:
    "ca8fd84b-cb98-4325-90d4-e8702ab687bd:v0Grio0HSsxUM60PiYjvJkkWeqeH4rVRYwyvTmjZr+E="
})

module.exports = chatkit
