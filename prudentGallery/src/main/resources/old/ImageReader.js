/**
 * Returns all subfolders for the given folder.
 * 
 * @param folder
 * @returns
 */
function getGalleries(folder) {
	var folder = java.io.File(folder);
	if (!folder.exists() || folder.isFile()) {
		return [];
	}
	var result = new Array()
	var files = folder.listFiles()
	for (file in files) {
		if (files[file].isDirectory()) {
			result.push(files[file].name);
		}
	}
	return result
}

/**
 * @param folder
 * @returns All images within the given folder.
 */
function getPictures(folder) {
	var folder = java.io.File(folder);
	if (!folder.exists() || folder.isFile()) {
		return [];
	}
	var result = new Array()
	var files = folder.listFiles()
	for (file in files) {
		if (files[file].isFile()
				&& isImage(files[file].getName().toLowerCase())) {
			result.push(files[file].name);
		}
	}
	return result
}

function isImage(fileName) {
	return fileName.endsWith('.jpg') || fileName.endsWith('.gif')
			|| fileName.endsWith('.png') || fileName.endsWith('.jpeg');
}