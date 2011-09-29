var cacheFolder = application.globals
		.get('com.threecrickets.prudence.DelegatedResource.fileUploadDirectory')
		+ '/../../../cache/scrobbleGallery/'

function handleInit(conversation) {
	// conversation.addMediaTypeByName('image/jpg')
}

function handleGet(conversation) {
	var fileName = application.globals
			.get('com.threecrickets.prudence.DelegatedResource.fileUploadDirectory')
			+ '/' + conversation.query.get('image')
	switch (String(conversation.query.get('size'))) {
	case 'full':
		return org.apache.commons.io.FileUtils
				.readFileToByteArray(new java.io.File(fileName))
	case 'large':
		return getImage(fileName, 800, 600, true)
	case 'small':
	default:
		return getImage(fileName, 200, 150, false)
	}
}

/**
 * Loads and caches the image.
 */
function getImage(fileName, width, height, preserveAspectRatio) {
	var file = new java.io.File(cacheFolder + width + '_' + height + '_'
			+ preserveAspectRatio + '/image'
			+ new java.lang.String(fileName).hashCode() + '.jpg')
	if (!file.exists()) {
		Packages.me.scrobble.images.ImageUtils.resizeImage(new java.io.File(
				fileName).getCanonicalPath(), file.getCanonicalPath(), width,
				height, preserveAspectRatio);
	}
	return org.apache.commons.io.FileUtils.readFileToByteArray(file);
}