package grails.plugin.facebookcomments

import groovy.xml.MarkupBuilder

class FacebookCommentTagLib {

	static namespace = "fb"

	def grailsApplication

	def comments = { attrs ->

		def settings = grailsApplication.config.grails.plugin?.facebookcomments

		def width = attrs.width ?: settings?.width
		def colorscheme = attrs.colorscheme ?: settings?.colorscheme
		def num_posts = attrs.num_posts ?: settings?.num_posts
		def mobile =    attrs.mobile ?: settings?.mobile

		def href = attrs.href
		if(attrs.href) {
			href = attrs.href
		} else if(settings.href instanceof Closure) {
			href = settings.href(attrs.bean)
		} else {
			href = request.getRequestURL()
		}

		Map fbAttrs = ["class":"fb-comments"]

		addFbAttr(fbAttrs, "width", width)
		addFbAttr(fbAttrs, "colorscheme", colorscheme)
		addFbAttr(fbAttrs, "num_posts", num_posts)
		addFbAttr(fbAttrs, "mobile", mobile)
		addFbAttr(fbAttrs, "href", href)

		new MarkupBuilder(out).div(fbAttrs)
	}

	def initFbCommentsJS = {attrs ->
		def settings = grailsApplication.config.grails.plugin?.facebookcomments

		def appId
		if(attrs.appId) {
			appId = attrs.appId
		} else {
			appId = settings?.appId
		}

		if(!appId) {
			throwTagError("Facebook appId is neither configured in config nor specified in tag")
		}

		out << render(template:"/templates/fb-comment/fb-comment-js", contextPath: pluginContextPath, model:[appId:appId])
	}

	private void addFbAttr(Map attrs, name, value) {
		if(name && value) {
			name = "data-"+name
			attrs[name] = value
		}
	}
}
