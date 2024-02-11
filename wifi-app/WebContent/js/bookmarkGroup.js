function validateForm() {
    var selectedValue = document.getElementById("bookmarkGroup").value;
    if (selectedValue === "0") {
        return false; 
    }
    return true;
}

function deleteBookMark(dataId) {
    if (confirm("삭제하시겠습니까?")) {
        window.location.href = "delete-bookmark-group.jsp?bookmarkGroupId=" + dataId;
	}
}

document.getElementById('orderNo').addEventListener('input', function() {
    var orderNo = this.value;
    if (!/^\d+$/.test(orderNo)) {
        alert('숫자만 입력해주세요.');
        this.value = '';
    }
});

document.getElementById('orderNo').addEventListener('keydown', function(event) {
    if (event.keyCode === 8) {
        if (document.querySelector('.alert')) {
            document.querySelector('.alert').style.display = 'none';
        }
    }
});