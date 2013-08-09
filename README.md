grails-facebook-comments
========================

Facebook comments grails plugin


## Configuration
Following configuration options are available. 

- grails.plugin.facebookcomments.appId
- grails.plugin.facebookcomments.width
- grails.plugin.facebookcomments.colorscheme
- grails.plugin.facebookcomments.num_posts
- grails.plugin.facebookcomments.mobile
- grails.plugin.facebookcomments.href = { bean ->  }



All of the config options are optional.
See [facebook documentation](https://developers.facebook.com/docs/reference/plugins/comments/) for details about possible values.

## Integrating facebook comments into your application.
Follow this steps for integration

*Step 1*
Put `<fb:initFbCommentsJS appId="your app id" />` right after opening `<body>` tag. It will initialize facebook comments js sdk.
appId parameter is optional and if not specified it will be picked up from config.

*step 2*
put `<fb:comments width="" colorscheme="", num_posts="" mobile="" href="http://example.com"/>` anywhere in the page where you want comments to be displayed.
Again all of the parameters are optional and if not specified they will be picked up from config.

#### Configuring data-href url
-----
href parameter is used identify the page for comments. It can directly specified in `<fb:comments>` tag, or it can be configured as a Closure in config.
When no href parameter is passed to `<fb:comments>` tag, it will check if Closure is configured in config, if so, the closure will be called and return value will be used as href.

*Example*
```
Config.groovy
grails.plugin.facebookcomments.href = { bean ->  "http://example.com/page?id=$bean.id" }

```

And now in your gsp file

```
<fb:comments bean="${page}" />

```

When href is not passed as parameter to `<fb:comments />` or not configured in Config.groovy, current request url will be used as href.
