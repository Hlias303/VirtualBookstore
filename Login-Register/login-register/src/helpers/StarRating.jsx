import React, { useState } from 'react';
import Rating from '@mui/material/Rating';
import StarIcon from '@mui/icons-material/Star';
import Box from '@mui/material/Box';

// Reusable star rating bar (out of 5 stars).
// Props:
//   - value:    the current rating (0-5)
//   - onChange: optional callback (newValue) => ...; when provided the bar is
//               interactive (the user can pick a rating), otherwise read-only
//   - readOnly: force read-only display
//   - size:     MUI size ("small" | "medium" | "large")
//   - label:    optional text shown before the stars (e.g. "Your Rating:")
function StarRating({ value, onChange, readOnly = false, size = 'medium', label }) {
  const [hover, setHover] = useState(-1);

  const interactive = !readOnly && typeof onChange === 'function';

  return (
    <Box sx={{ display: 'flex', alignItems: 'center', gap: 1, mt: 1 }}>
      {label && <span style={{ fontWeight: 600, color: '#333' }}>{label}</span>}
      <Rating
        name="star-rating"
        value={Number(value) || 0}
        precision={1}
        max={5}
        size={size}
        readOnly={!interactive}
        icon={<StarIcon fontSize="inherit" sx={{ color: '#FFB400' }} />}
        emptyIcon={<StarIcon fontSize="inherit" sx={{ color: '#ddd' }} />}
        onChange={(event, newValue) => interactive && onChange(newValue)}
        onChangeActive={(event, newHover) => setHover(newHover)}
      />
      {interactive && hover !== -1 && (
        <span style={{ color: '#FFB400', fontWeight: 600 }}>{hover}/5</span>
      )}
    </Box>
  );
}

export default StarRating
