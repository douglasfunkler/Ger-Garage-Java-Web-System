$('#deletionConfirmationModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var bookingCode = button.data('code');
	var bookingDescription = button.data('description');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.attr('action');
	
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + bookingCode);
	
	modal.find('.modal-body span').html('Are you sure you want to delete the booking <strong>' + bookingDescription + '</strong>?');
});

$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney();
	
	$('.js-update-status').on('click', function(event) {
		event.preventDefault();
		
		var receiveButton = $(event.currentTarget);
		var receiveUrl = receiveButton.attr('href');
		
		var response = $.ajax({
			url: receiveUrl,
			type: 'PUT'
		});
		
		
		response.done(function(e) {
			var bookingCode = receiveButton.data('code');
			$('[data-role=' + bookingCode + ']').html('<span class="label label-success">' + e + '</span>');
			receiveButton.hide();
		});
		
		response.fail(function(e) {
			console.log(e);
			alert('Error');
		});
		
	});
});
