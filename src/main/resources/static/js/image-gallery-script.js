var list = document.querySelector('.slider .list');
var items = document.querySelectorAll('.slider .item');
var prev = document.getElementById('prev');
var next = document.getElementById('next');
var active = 0;
var lengthItems = items.length; // Use the length directly
var itemsIteration = document.querySelector('span');
var uploadBtn = document.getElementById('uploadBtn');

next.onclick = function () {
    active = (active + 1) % lengthItems; // Wrap around using modulo
    reloadSlider();
    console.log('active ' + active + ' Length ' + lengthItems);
}

prev.onclick = function () {
    active = (active - 1 + lengthItems) % lengthItems; // Wrap around using modulo
    reloadSlider();
    console.log('active ' + active + ' Length ' + lengthItems);
}
uploadBtn.onclick = function () {
    var patientId= itemsIteration.getAttribute('patientId');
     location.href = '/upload?patientId=' + patientId;
     console.log("the redirect has happened, patientId = "+ patientId);
 }
/**This function is having issues when displaying the uploadDate and uploader**/
function reloadSlider() {
    var checkLeft = items[active].offsetLeft;
    console.log('check left by: ' + checkLeft);
    list.style.left = -checkLeft + 'px'; // Move the list to show the active item
    var currentItem = items[active].querySelector('img')
    var uploadDate = currentItem.getAttribute('data-upload-date');
    var uploader = currentItem.getAttribute('data-uploader');
    document.getElementById('uploadDate').textContent = "Date Uploaded: " + uploadDate;
    document.getElementById('uploader').textContent = "Uploaded By: " + uploader;
    console.log("The upload Date: "+uploadDate)
    console.log("The Uploader: "+uploader)
}
