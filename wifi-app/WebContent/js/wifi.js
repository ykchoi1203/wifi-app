function confirmDelete(historyId) {
    var confirmation = confirm("삭제하시겠습니까?");
    if (confirmation) {
        document.getElementById("deleteForm_" + historyId).submit();
    }
}

