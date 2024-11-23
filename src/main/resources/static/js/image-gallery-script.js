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
/*uploadBtn.onclick = function () {
    var patientId= itemsIteration.getAttribute('patientId');
     location.href = '/upload?patientId=' + patientId;
     console.log("the redirect has happened, patientId = "+ patientId);
 }*/
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
/*new source code starts here*/
        const myImage = document.getElementById('viewBtn');//changed the id to viewBtn from myImage
        const fullScreenContainer = document.getElementById('fullScreenContainer');
        const fullScreenImg = document.getElementById('fullScreenImg');
        const closeButton = document.getElementById('closeButton');
        const zoomInButton = document.getElementById('zoomInButton');
        const zoomOutButton = document.getElementById('zoomOutButton');
        const zoomControls = document.getElementById('zoomControls');

        let scale = 1;
        const zoomIntensity = 0.1;
        const minScale = 1;
        const maxScale = 5;

        myImage.addEventListener('click', () => {

            fullScreenImg.src = items[active].querySelector('img').src;//changed items[active].querySelector('img') from myImage.src
            fullScreenContainer.style.display = 'block';
            fullScreenContainer.style.position='absolute';
            fullScreenImg.style.position='absolute';
            closeButton.style.position='absolute';
            zoomControls.style.position='absolute';
            resetImage();
        });

        closeButton.addEventListener('click', () => {
            fullScreenContainer.style.display = 'none';
        });

        function startDrag(e) {
            isDragging = true;
            startX = e.clientX || e.touches[0].clientX;
            startY = e.clientY || e.touches[0].clientY;
            initialX = fullScreenImg.offsetLeft;
            initialY = fullScreenImg.offsetTop;
            fullScreenImg.style.cursor = 'grabbing';
        }

        function drag(e) {
            if (isDragging) {
                const clientX = e.clientX || e.touches[0].clientX;
                const clientY = e.clientY || e.touches[0].clientY;
                const dx = clientX - startX;
                const dy = clientY - startY;
                fullScreenImg.style.left = initialX + dx + 'px';
                fullScreenImg.style.top = initialY + dy + 'px';
            }
        }

        function endDrag() {
            isDragging = false;
            fullScreenImg.style.cursor = 'grab';
        }

        fullScreenImg.addEventListener('mousedown', startDrag);
        fullScreenImg.addEventListener('touchstart', startDrag);
        window.addEventListener('mousemove', drag);
        window.addEventListener('touchmove', drag);
        window.addEventListener('mouseup', endDrag);
        window.addEventListener('touchend', endDrag);

        function resetImage() {
            scale = 1;
            fullScreenImg.style.transform = `translate(-50%, -50%) scale(${scale})`;
            fullScreenImg.style.left = '50%';
            fullScreenImg.style.top = '50%';
        }

        // Zoom in and out functionality
        zoomInButton.addEventListener('click', () => {
            scale = Math.min(scale + zoomIntensity, maxScale);
            updateImageTransform();
        });

        zoomOutButton.addEventListener('click', () => {
            scale = Math.max(scale - zoomIntensity, minScale);
            updateImageTransform();
        });

        function updateImageTransform() {
            fullScreenImg.style.transform = `translate(-50%, -50%) scale(${scale})`;
        }

        fullScreenContainer.addEventListener('wheel', (e) => {
            e.preventDefault();
            const mouseX = e.clientX - fullScreenImg.getBoundingClientRect().left;
            const mouseY = e.clientY - fullScreenImg.getBoundingClientRect().top;

            const newScale = e.deltaY < 0 ? scale + zoomIntensity : scale - zoomIntensity;
            scale = Math.min(Math.max(newScale, minScale), maxScale); // Limit zoom scale

            fullScreenImg.style.transform = `translate(-50%, -50%) scale(${scale})`;
            fullScreenImg.style.left = `${mouseX}px`;
            fullScreenImg.style.top = `${mouseY}px`;
        });

